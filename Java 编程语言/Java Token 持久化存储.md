## Token持久化存储

我们之前使用SpringSecurity时，remember-me的Token是支持持久化存储的，而我们当时是存储在数据库中，那么Token信息能否存储在缓存中呢，当然也是可以的，我们可以手动实现一个：

```java
//实现PersistentTokenRepository接口
@Component
public class RedisTokenRepository implements PersistentTokenRepository {
   //Key名称前缀，用于区分
    private final static String REMEMBER_ME_KEY = "spring:security:rememberMe:";
    @Resource
    RedisTemplate<Object, Object> template;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
       //这里要放两个，一个存seriesId->Token，一个存username->seriesId，因为删除时是通过username删除
        template.opsForValue().set(REMEMBER_ME_KEY+"username:"+token.getUsername(), token.getSeries());
        template.expire(REMEMBER_ME_KEY+"username:"+token.getUsername(), 1, TimeUnit.DAYS);
        this.setToken(token);
    }

   //先获取，然后修改创建一个新的，再放入
    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentRememberMeToken token = this.getToken(series);
        if(token != null)
           this.setToken(new PersistentRememberMeToken(token.getUsername(), series, tokenValue, lastUsed));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return this.getToken(seriesId);
    }

   //通过username找seriesId直接删除这两个
    @Override
    public void removeUserTokens(String username) {
        String series = (String) template.opsForValue().get(REMEMBER_ME_KEY+"username:"+username);
        template.delete(REMEMBER_ME_KEY+series);
        template.delete(REMEMBER_ME_KEY+"username:"+username);
    }

  
   //由于PersistentRememberMeToken没实现序列化接口，这里只能用Hash来存储了，所以单独编写一个set和get操作
    private PersistentRememberMeToken getToken(String series){
        Map<Object, Object> map = template.opsForHash().entries(REMEMBER_ME_KEY+series);
        if(map.isEmpty()) return null;
        return new PersistentRememberMeToken(
                (String) map.get("username"),
                (String) map.get("series"),
                (String) map.get("tokenValue"),
                new Date(Long.parseLong((String) map.get("date"))));
    }

    private void setToken(PersistentRememberMeToken token){
        Map<String, String> map = new HashMap<>();
        map.put("username", token.getUsername());
        map.put("series", token.getSeries());
        map.put("tokenValue", token.getTokenValue());
        map.put("date", ""+token.getDate().getTime());
        template.opsForHash().putAll(REMEMBER_ME_KEY+token.getSeries(), map);
        template.expire(REMEMBER_ME_KEY+token.getSeries(), 1, TimeUnit.DAYS);
    }
}
```

接着把验证Service实现了：

```java
@Service
public class AuthService implements UserDetailsService {

    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = mapper.getAccountByUsername(username);
        if(account == null) throw new UsernameNotFoundException("");
        return User
                .withUsername(username)
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
}
```

Mapper也安排上：

```java
@Data
public class Account implements Serializable {
    int id;
    String username;
    String password;
    String role;
}
```

```java
@CacheNamespace(implementation = MybatisRedisCache.class)
@Mapper
public interface UserMapper {

    @Select("select * from users where username = #{username}")
    Account getAccountByUsername(String username);
}
```

最后配置文件配一波：

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .rememberMe()
            .tokenRepository(repository);
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(service)
            .passwordEncoder(new BCryptPasswordEncoder());
}
```

OK，启动服务器验证一下吧。
