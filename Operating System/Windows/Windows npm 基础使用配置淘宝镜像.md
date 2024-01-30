# npm 基础使用配置淘宝镜像

- 查看 npm 配置文件

    ```sh
    npm config list
    ```

- 配置 npm 使用淘宝镜像

    ```sh
    npm config set registry http://registry.npm.taobao.org/
    ```

- 安装依赖

    ```sh
    npm install
    ```

    **注意**

    - 大家如果 npm install 安装依赖出现 chromedriver 之类问题，先在项目里运行下面命令
        `npm install chromedriver --chromedriver_cdnurl=http://cdn.npm.taobao.org/dist/chromedriver` 然后再运行 `npm install`
    - nvm 其他版本 `npm install` 过，建议删除 node_modules 在重新 `npm install` 避免其他版本影响项目无法正常启动

- 运行项目

    ```sh
    npm run dev
    ```
