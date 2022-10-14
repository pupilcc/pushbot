# pushbot

该项目是基于 Telegram Bot 的消息推送 bot，可将消息通过 GET/POST 网络请求经由 Telegram Bot 推送至用户。可用于服务器监控报警、抢票通知、每日天气推送等应用场景。

提供了 [Docker 镜像](https://hub.docker.com/r/pupilcc/pushbot)，便于自建，也可直接使用我提供的 Bot，欢迎品尝~（[点击此处添加 Bot](https://t.me/notification_me_bot))

## 功能

* [推送消息](#推送消息)
* [推送模板消息](#推送模板消息)
* [推送 Docker Hub 自动构建成功消息](#DockerHub)

## 食用指南

### Bot 命令说明

* `/start`：生成/显示推送链接
* `/end`：删除推送链接

生成推送链接后，请将下列链接中的`{chatToken}`替换成自己推送链接末尾专属的随机字符串，即可使用。

### 推送消息

接口地址：`/sendMessage/{chatToken}`

**参数说明：**

参数|类型|必须|说明
-|-|-|-
text|String|是|文字内容(当 photo 存在时可为空)
photo|String|否|图片文件或者图片外链
parseMode|String|否|发送文字内容的样式，可以是 Markdown 或 HTML

```bash
# using get
# 推送消息
curl -X GET https://pb.pupilcc.app/sendMessage/{chatToken}?text=HelloWorld
# 推送图片
curl -X GET https://pb.pupilcc.app/sendMessage/{chtToken}?photo=https://xxx.com/xxxxx.jpg

# using post
# 推送消息
curl -d "text=HelloWorld" -X POST https://pb.pupilcc.app/sendMessage/{chatToken}
# 推送图片
curl -d "photo=https://xxx.com/xxxxx.jpg" -X POST https://pb.pupilcc.app/sendMessage/{chatToken}
```

### 推送模板消息

接口地址：`/sendTemplate/{chatToken}`

**参数说明：**

参数|类型|必须|说明
-|-|-|-
templateId|String|是|模板id
title|String|是|标题
content|String|否|内容

[查看现有模板样式](https://github.com/pupilcc/pushbot/blob/master/docs/template.md)

### <span id="DockerHub">推送 Docker Hub 自动构建成功消息</span>

接口地址：`/webhook/docker/{chatToken}`

将该 URL 填写至 Docker Hub 仓库的 Webhooks URL 内即可，当 Docker Hub 自动构建成功后就会推送消息。

![docker-webhook](https://vip1.loli.io/2022/01/20/RLbr24NPfUc19Gv.png)

### 隐私相关

Bot 不会识别和储存任何用户推送的消息，只会将推送消息发送给 Telegram 服务器。Bot 只会记录用户回话 id，此 id 是向 Telegram 推送消息的凭据。


## 自建指南

### 食材

* 一个域名
* 一台可访问 Telegram 的服务器
* Linux 基础知识

### 菜谱

#### 创建 Bot Token

点击 [@BotFather](https://telegram.me/BotFather)，按步骤创建自己的 Bot 并获取 Token

#### 创建 sqlite3 数据库 pushbot.db

```
# 进入存放数据库文件的目录
cd /opt
# pushbot.db
sqlite3 pushbot.db

sqlite> CREATE TABLE users (chatId int unique, chatToken text unique);
sqlite> .quit
```

#### 使用 Docker 部署

需要替换 `BOT_TOKEN` 和 `BOT_DOMAIN` 的内容，以及数据库的路径。

docker-compose

```yaml
version: '3.7'
services:
  pushbot:
    image: pupilcc/pushbot
    container_name: pushbot
    restart: unless-stopped
    volumes:
      # 创建好的数据库绝对路径
      - /opt/pushbot.db:/app/pushbot/pushbot.db
    ports:
      - "25701:25701"
    environment:
      # Bot Token
      - BOT_TOKEN=<TOKEN>
      # 自己的域名
      - BOT_DOMAIN=<DOMAIN>
```

docker run

```bash
docker run -d \
    --name pushbot \
    --restart unless-stopped \
    -p 25701:25701 \
    -e BOT_TOKEN=<TOKEN> \
    -e BOT_DOMAIN=<DOMAIN> \
    -v /opt/pushbot.db:/app/pushbot/pushbot.db \
    pupilcc/pushbot
```

#### 使用 Nginx 反向代理

在域名对应的 nginx 配置文件 443 端口处添加反向代理：

```
# 反向代理 Docker 指定端口
location / {
    proxy_pass         http://127.0.0.1:25701;
    proxy_set_header   Host             $host;
    proxy_set_header   X-Real-IP        $remote_addr;
    proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
}
```

#### 设置 WebHook

请将以下链接中的 `{botToken}` 替换为 Bot Token，`{domian}` 替换为自己设置的域名，替换好后在浏览器访问该链接。

```
https://api.telegram.org/bot{botToken}/setWebhook?url={domain}/webhook/{botToken}
```

提示如下信息，则代表设置成功，即可尽情享用。

```json
{
  "ok": true,
  "result": true,
  "description": "Webhook is already set"
}
```

## 感谢

* [Fndroid/tg_push_bot](https://github.com/Fndroid/tg_push_bot)