#!/bin/sh

# 这个脚本尝试使用常用的 *cat* 命令来预览以下文件类型，让你无需离开舒适的终端：
# - 纯文本
# - Markdown
# - 图片
# - 视频
# 使用的工具如下：
# bat               -> 纯文本        -> install `cargo install bat`
# glow              -> Markdown     -> install `sudo snap install glow`
# chafa             -> 图片          -> install `sudo apt install chafa`
# ffmpegthumbnailer -> 视频          -> install `sudo snap install glow`
#
# 默认的 GNOME Terminal 不支持 Sixel，因此图片/视频预览可能无法显示。
# 推荐使用支持 Sixel 的终端，如：mlterm（安装：sudo apt install mlterm）


FILE_LEN=$#
# 将"$@"作为一个整体字符串保存，避免空格问题，但注意这会把多个文件变成一个字符串。
# 更好的方式是保留"$@"的列表特性，在函数中通过遍历处理。
# 这里我们调整策略：不将"$@"合并，而是直接传递列表。
# 为了健壮性，我们不在主入口合并参数，而是在函数中遍历。
DEPENDENCIES="bat glow chafa ffmpegthumbnailer"

# 依赖检查函数
dependencies_check() {
    for i in $DEPENDENCIES; do
        if ! command -v "$i" >/dev/null 2>&1; then
            echo "错误：命令 '$i' 未找到！" >&2
            return 1
        fi
    done
}

# 预览单个文件
preview_onefile() {
    file="$1"
    # 检查文件是否存在且为普通文件（若不是，则静默退出）
    [ -f "$file" ] || exit 0

    # 首先通过扩展名检查 Markdown 文件
    case "$file" in
        *.md|*.markdown|*.MD)
            glow "$file"
            exit 0
            ;;
    esac

    # 通过 MIME 类型检查文件格式
    FILE_TYPE=$(file --brief --mime-type -- "$file")
    case "$FILE_TYPE" in
        image/*)
            # 使用 sixel 格式输出图片
            chafa --format sixels "$file"
            ;;
        video/*)
            # 生成视频缩略图（JPEG 格式，尺寸 0 表示使用默认大小），并通过管道传给 chafa 显示
            # 错误输出重定向到 /dev/null，避免干扰
            if ! ffmpegthumbnailer -i "$file" -o - -c jpeg -s 0 2>/dev/null | chafa -f sixel -; then
                # 如果 ffmpegthumbnailer 失败，尝试用 bat 显示文件信息作为后备
                echo "（无法生成视频缩略图，显示文件信息）"
                bat -pp "$file" 2>/dev/null || file -- "$file"
            fi
            ;;
        *)
            # 其他所有类型默认用 bat 显示纯文本（-pp：普通显示，无分页器）
            bat -pp "$file"
            ;;
    esac
}

# 预览多个文件
preview_multifile() {
    # 为了效率，只检查第一个文件的类型，假设所有文件类型一致
    first_file="$1"
    FILE_TYPE=$(file --brief --mime-type -- "$first_file")

    case "$FILE_TYPE" in
        image/*)
            # 显示所有图片，启用标签显示文件名，自动适应网格布局
            chafa --label on --grid auto --format sixels "$@"
            ;;
        video/*)
            # 循环处理每个视频文件
            for video in "$@"; do
                echo "=== $video ==="
                ffmpegthumbnailer -i "$video" -o - -c jpeg -s 800 2>/dev/null | chafa -f sixel -
                # 如果上面的命令失败，没有后备输出，保持简洁
            done
            ;;
        *)
            # 对于非图片/视频的多文件，可能是文本。用 bat 逐个显示，并加上文件名分隔
            for text_file in "$@"; do
                echo "=== $text_file ==="
                bat -pp "$text_file"
            done
            ;;
    esac
}

main() {
    # 检查依赖
    dependencies_check || exit 1

    # 如果没有提供参数，退出
    [ $# -eq 0 ] && exit 1

    if [ $# -eq 1 ]; then
        # 只有一个文件，调用单文件预览函数
        preview_onefile "$1"
    else
        # 有多个文件，调用多文件预览函数
        preview_multifile "$@"
    fi
}

# 将位置参数列表传递给 main 函数
main "$@"