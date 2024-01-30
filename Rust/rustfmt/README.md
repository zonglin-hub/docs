# Rust fmt

```tomls
# See more keys and their definitions at https://rust-lang.github.io/rustfmt/

# 回退到垂直格式之前数组文本的最大宽度。
# array_width = 60

# 在回退到垂直格式之前，类似函数的属性的参数的最大宽度。
# attr_fn_like_width = 70

# 当二进制表达式变为多行时，将二进制运算符放在哪里。
# binop_separator = "Front"

# 必须在项目之间放置的最小空行数。如果两个项目之间的空行较少，则会插入其他空行。
# blank_lines_lower_bound = 0

# 可以在项目之间放置的最大空行数。如果找到的连续空行数超过此数目，则会将其剪裁以匹配此整数。
# blank_lines_upper_bound = 1

# 项目的支架样式
# brace_style = "SameLineWhere"

# 一条链条的最大宽度可以放在一条线上。
# chain_width = 60

# 是否使用彩色输出。
# color = "Auto"

# 将控件表达式与函数调用相结合。
# combine_control_expr = true

# 注释的最大长度。除非 wrap_comments = true。
# comment_width = 80

# 控制流构造的支撑样式
# control_brace_style = "AlwaysSameLine"

# 不要重新格式化任何东西。
# disable_all_formatting = false

# 指定分析程序使用的版本。
# edition = "2021"

# 将空体函数和实现放在一行中
# empty_item_single_line = true

# 具有判别性的枚举变体的最大长度，与其他变量垂直对齐。出于对齐目的，将忽略没有判别器的变体。
# enum_discrim_align_threshold = 0

# 如果 Rustfmt 无法获取 max_width 中的所有行，则出错，注释和字符串文字除外。
# 如果发生这种情况，那么它是 Rustfmt 中的一个错误。
# 您可以通过重构代码来避免长/复杂的表达式来解决该 bug，通常是通过提取局部变量或使用较短的名称。
# error_on_line_overflow = false

# 如果无法在 中 max_width 获取注释或字符串文字，或者它们留下尾随空格，则出错。
# error_on_unformatted = false

# 此选项已弃用，并已重命名， fn_params_layout 以便更好地传达它会影响函数签名中参数的布局。
# fn_params_layout = "Tall"

# 回退到垂直格式之前函数调用的参数的最大宽度。
# fn_call_width = 60

# 将单表达式函数放在一行上
# fn_single_line = true

# 始终打印外部项目的 abi
# force_explicit_abi = false

# 强制多线闭合和匹配臂体包裹在一个块中
# force_multiline_blocks = false

# 设置文档注释中包含的代码片段的格式。
# format_code_in_doc_comments = true

# 文档注释中包含的代码片段的最大宽度。仅当为 true 时 format_code_in_doc_comments 才使用。
# doc_comment_code_block_width = 100

# 格式化生成的文件。
# 如果前五行中的任何一行包含 @generated 注释标记，则认为已生成文件。
# 默认情况下，生成的文件会重新格式化，即忽略 @generated 标记。
# 此选项当前在 stdin 中被忽略（ @generated 在 stdin 中被忽略。
# format_generated_files = false

# 在宏中格式化元变量匹配模式。
# format_macro_matchers = false

# 设置声明性宏定义正文的格式。
# format_macro_bodies = true

# 跳过使用以下名称设置宏调用正文的格式。
# skip_macro_invocations = []

# 必要时设置字符串文本的格式
# format_strings = true

# 使用制表符进行缩进，使用空格进行对齐
# hard_tabs = true

# 控制十六进制文本值中字母的大小写
# hex_literal_case = "Preserve"

# 如果解析器无法解析文件，则显示解析错误。
# show_parse_errors = true

# 跳过与指定模式匹配的文件和目录的格式设置。
# 模式格式与 .gitignore 相同。请务必使用 Unix/正斜杠 / 样式路径。
# 此路径样式适用于所有平台。不支持带有反斜杠 \ 的 Windows 样式路径。
# ignore = ["examples"]

# 导入的缩进样式
# imports_indent = "Visual"

# 导入块内的项目布局
# imports_layout = "Mixed"

# 在表达式或项上缩进。
# indent_style = "Visual"

# 如果项目及其属性的总宽度低于阈值，则将项目及其属性写在同一行上
# inline_attribute_width = 0

# 控制在主体的第一行无法与 => 操作员在同一行上的情况下是否缠绕臂体。
# match_arm_blocks = false

# 控制是否在火柴臂上包含引导管
# match_arm_leading_pipes = "Never"

# 在基于块的匹配臂后放置一个尾随逗号（非块臂不受影响）
# match_block_trailing_comma = true

# 每行的最大宽度
# max_width = 100

# 将多个派生合并为一个派生。
# merge_derives = false

# 控制导入在语句中的 use 结构。导入将被合并或拆分到配置的粒度级别。
# imports_granularity = "Preserve"

# Unix 或 Windows 行尾
# newline_style = "Auto"

# 在可能的情况下，将 /* */ 注释转换为 // 注释
# normalize_comments = true

# 将 #![doc] 和 属性转换为 //! 和 /// #[doc] doc 注释。
# normalize_doc_attributes = true

# 当结构、切片、数组和块/类似数组的宏用作表达式列表中的最后一个参数时，允许它们溢出（如块/闭包），而不是缩进到新行上。
# overflow_delimited_expr = true

# 删除嵌套的 parens。
# remove_nested_parens = true

# 对 impl 项重新排序。 type 放在 const 第一位，然后是宏和方法。
# reorder_impl_items = true

# 按组的字母顺序对 import 和 extern crate 语句重新排序（组之间用换行符分隔）。
# reorder_imports = true

# 放弃现有的导入组，并为以下对象创建三个组：
# group_imports = "Preserve"

# 需要特定版本的 rustfmt。如果要确保在 CI 中使用特定版本的 rustfmt，请使用此选项。
# required_version = "1.4.38"

# 要被视为“短”的数组元素的宽度阈值。
# short_array_element_width_threshold = 10

# 不要重新格式化线外模块
# skip_children = true

# 替换 try！Macro 由 ？速记
# use_try_shorthand = true

# 单行 if-else 表达式的最大行长度。值为 0 （零） 会导致 if-else 表达式始终被分解为多行。
# 请注意，当 use_small_heuristics 设置为 Off 时会发生这种情况。
# single_line_if_else_max_width = 50

# 单行 let-else 语句的最大行长度。
# 请参阅 Rust 风格指南的 let-else 语句部分，了解有关何时可以在一行上编写 let-else 语句的更多详细信息。
# 值 0 （零） 表示发散 else 块将始终在多行上格式化。
# 请注意，当 use_small_heuristics 设置为 Off 时会发生这种情况。
# single_line_let_else_max_width = 50

# 在冒号后留一个空格。
# space_after_colon = true

# 在冒号前留一个空格。
# space_before_colon = true

# 在 ..， .. 周围放置空格。=，并且......范围运算符
# spaces_around_ranges = true

# 要相互对齐的结构字段之间的最大宽度差异。
# struct_field_align_threshold = 0

# 将小结构文字放在一行上
# struct_lit_single_line = false

# 在回退到垂直格式之前，结构文本正文中的最大宽度。值 0 （零） 会导致结构文字始终被分解为多行。
# 请注意，当 use_small_heuristics 设置为 Off 时会发生这种情况。
# struct_lit_width = 18

# 在回退到垂直格式之前，结构变体正文中的最大宽度。值 0 （零） 会导致结构文字始终被分解为多行。
# 请注意，当 use_small_heuristics 设置为 Off 时会发生这种情况。
# struct_variant_width = 35

# 每个选项卡的空格数
# tab_spaces = 4

# 如何处理列表的尾随逗号
# trailing_comma = "Always"

# 在中断、继续和返回后添加尾随分号
# trailing_semicolon = true

# 确定类型标点符号中是否或 = 是否 + 换行在空格中
# type_punctuation_density = "Compressed"

# 在不稳定通道上启用不稳定功能。
# unstable_features = true

# 如果可能，请使用字段初始化速记。
# use_field_init_shorthand = true

# 此选项可用于简化粒度宽度配置设置
# fn_call_width, struct_lit_width, array_width, chain_width, attr_fn_like_width, struct_variant_width, single_line_if_else_max_width
# 的管理和批量更新，这些设置分别控制格式化结构何时基于宽度进行多行或垂直。
use_small_heuristics = "Max"

# 控制在主体的第一行无法与 => 操作员在同一行上的情况下是否缠绕臂体。
# 重新排序模块列表
reorder_modules = true

# 将 _ 通配符的字符串替换为单个 ..在元组模式中
condense_wildcard_suffixes = true
```
