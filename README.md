# pushbot

该项目是基于 Telegram Bot 的消息推送 bot，可将消息通过 GET/POST 网络请求经由 Telegram Bot 推送至用户。可用于服务器监控报警、抢票通知、每日天气推送等应用场景。

提供了 [Docker 镜像](https://hub.docker.com/r/pupilcc/pushbot)，便于自建，也可直接使用我提供的 Bot，欢迎品尝~（[点击此处添加 Bot](https://t.me/notification_me_bot))

该项目是将 [@Fndroid](https://github.com/Fndroid) 停更的 [tg_push_bot](https://github.com/Fndroid/tg_push_bot) 项目重写，同时提供 Docker 镜像便于部署，后续将探索更多功能。


## 食用指南

### Bot 命令说明

* `/start`：生成/显示推送链接
* `/end`：删除推送链接

### 推送消息

```bash
// using get
curl -X GET https://pushbot.pupilcc.com/sendMessage/:chatToken?text=HelloWorld

// using post
curl -d "text=HelloWorld" -X POST https://pushbot.pupilcc.com/sendMessage/:chatToken
```

### 参数说明

参数|类型|必须|说明
-|-|-|-
text|String|True|发送的文字内容
parse_mode|String|False|发送文字内容的样式，可以是 Markdown 或 HTML

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
// pushbot.db
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
      # 创建好的数据库绝对路径 /home/pushbot.db
      - /home/pushbot.db:/app/pushbot/pushbot.db
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
    -v /home/pushbot.db:/app/pushbot/pushbot.db \
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

例如：

```
server
    {
        listen 443 ssl http2;
        #listen [::]:443 ssl http2;
        server_name xxxx.com ;
        index index.html index.htm index.php default.html default.htm default.php;
        root  /home/wwwroot/xxxx.com;

        ssl_certificate /usr/local/nginx/conf/ssl/xxxx.com/fullchain.cer;
        ssl_certificate_key /usr/local/nginx/conf/ssl/xxxx.com/dl2.liacr.com.key;
        ssl_session_timeout 5m;
        ssl_protocols TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;
        ssl_prefer_server_ciphers on;
        ssl_ciphers "TLS13-AES-256-GCM-SHA384:TLS13-CHACHA20-POLY1305-SHA256:TLS13-AES-128-GCM-SHA256:TLS13-AES-128-CCM-8-SHA256:TLS13-AES-128-CCM-SHA256:EECDH+CHACHA20:EECDH+CHACHA20-draft:EECDH+AES128:RSA+AES128:EECDH+AES256:RSA+AES256:EECDH+3DES:RSA+3DES:!MD5";
        ssl_session_cache builtin:1000 shared:SSL:10m;
        # openssl dhparam -out /usr/local/nginx/conf/ssl/dhparam.pem 2048
        ssl_dhparam /usr/local/nginx/conf/ssl/dhparam.pem;

        include rewrite/none.conf;
        #error_page   404   /404.html;

        # Deny access to PHP files in specific directory
        #location ~ /(wp-content|uploads|wp-includes|images)/.*\.php$ { deny all; }

        include enable-php.conf;

        # 反向代理 Docker 指定端口
        location / {
            proxy_pass         http://127.0.0.1:25701;
            proxy_set_header   Host             $host;
            proxy_set_header   X-Real-IP        $remote_addr;
            proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
        }

        location ~ .*\.(gif|jpg|jpeg|png|bmp|swf)$
        {
            expires      30d;
        }

        location ~ .*\.(js|css)?$
        {
            expires      12h;
        }

        location ~ /.well-known {
            allow all;
        }

        location ~ /\.
        {
            deny all;
        }

        access_log off;
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