<!-- $size: 16:9 -->
<!-- page_number: true -->
<!-- $theme: gaia -->

# Let's Swagger

---
## Goals
- Swaggerって何か聞かれて答えられる程度の知識をみにつける
- 具体的に何ができるのか、HowとWhatを知る

---
## つゆばらい
---
- 序盤英語です
  - 翻訳が面倒でした。ごめんなさい
  - 英語見ると蕁麻疹出ちゃう人はよそ見しててください
    - でも、英語できないとエンジニア人生かなり辛いです
- Swaggerを使う準備として調べてみたことを発表しています

- ってことは、私自身の実績はないです
  - でも、参画中のプロジェクトでは使われています
- 自身のために勉強したことを発表用に仕立て直してます
  - そのため、興味の対象が偏ってます

---
## What's <font color="RED">Swagger</font> ?

---
### <font color="RED">Ansower</font> quoted from official Site

---
#### The World's <font color="RED">most popular</font> Api tooling.
> Swagger is the world’s largest framework of API developer tools for the OpenAPI Specification(OAS), enabling development across the entire API lifecycle, from design and documentation, to test and deployment.

- 要約すると(Restful)APIの設計、文書化、テスト、開発を網羅した世界最大のフレームワークって書いてある

---
##### 構成要素
- DESIGN : Swagger Editor
- BUILD : Swagger Codegen
- DOCUMENT : Swagger UI

---
#### DESIGN (with <font color="RED">Swagger Editor</font>)
> Design new APIs, or edit existing ones, in a powerful editor which visually renders your OAS/Swagger definition with concise, real time feedback and error handling.
- 要はAPIの設計をエディター上でパワフルにやれるって書いてある

demo -> https://editor.swagger.io

---
#### BUILD (with <font color="RED">Swagger Codegen</font>)
> Quickly build APIs by turning your OAS/Swagger definition into code, generating server stubs and client libraries in over 40 different languages, allowing for development and prototyping.
- 要約すると定義ファイルからコード生成できるよって書いてある

```bash
# demo
$ swagger-codegen generate \
-i http://petstore.swagger.io/v2/swagger.json \
-l nodejs-server \
-o ./ist-swagger-demo
$ cd ist-swagger-demo
$ tree .
$ npm install
$ node .
```
---
#### DOCUMENT (with <font color="RED">Swagger UI</font>)
> Visualize your API's resources from its OAS/Swagger definition and generate beautiful, interactive documentation that can be hosted in any environment, allowing your end consumers to easily get started with your API.
- 要約すると定義ファイルから綺麗な仕様書生成するよって書いてある

```bash
# Continuing the previous, execute followings
$ npm install
$ node .
# then, go to http://localhost:8080/docs/
```

---
## <font color="RED">なぜ</font>Swaggerを使うのか

---
### その１：<<font color="RED">API仕様書を元にコード化</font>できるから
  - YAML/JSONで仕様書が書ける
    - Good by Excel !
  - API定義ファイルから
    - 多数の言語のクライアントコードなどが生成できる
    - サーバもさくっと作れる
      - mockとして使うもよし、ベースに使ってもよし
  - トップダウン形式というらしい

---
### その２：<font color="RED">コードからでもAPI仕様書の自動生成</font>ができるから
  - Spring, JAX-RS, Servletに対応
    - [swagger-coreのGit](https://github.com/swagger-api/swagger-core)に明記されている
  - 既存ソースにアノテーション貼って、ちょっと設定用コードかくだけで動く
    - 途中からでも導入できる！（と期待している）
  - ボトムアップ形式というらしい

---
### その３：<font color="RED">デファクタスタンダード</font>だから
  - まだなってない気がするけど、たぶんなる、たぶん…。
  - フォーマットについて、一度学習すれば他の現場他の案件でも同じ
  - そのフォーマットを見慣れることになるので、見落としが減る

---
## 今回は<font color="RED">ボトムアップ</font>形式について見てみましょう
- サンプルアプリを作ってみた
  - SpringFoxを適用して、Swagger Spec自動生成
  - からのSwagger-UIで仕様書の出来栄えを見ていく

---
## デモ用サンプルアプリ
```bash
git clone https://github.com/kogayushi/swagger-demo.git
```

---
### どんなアプリか
#### Identity Manager
  - ID認証して認証情報を返すだけの簡単なお仕事
#### Framework
- Spring Boot
  - Spring MVC
#### Protocol
- Restful API

---
### とりあえず、完成したSwagger-UIを見てください
```bash
$ cd swagger-demo
$ ./gradlew bootRun
# go to http://localhost:8080/swagger-ui.html
```

---
### git commit logを追います
Swagger用アノテーションなしのプレーンな状態から、
アノテーションを張っていくごとに、
徐々に育っていく様子にご注目ください

https://github.com/kogayushi/swagger-demo

---
#### Step 1
- Apply Spring Fox for swagger
---
1. リクエスト／レスポンスともにパラメーターがわかりにくい
    - formからのパラメーターでは何を送るべきかわからない
    - サンプル値がない
    - model見ると全部optionalってありえない
1. 定義してない4XX Http Statusがならんでる
    - 401/403は使ってない
1. 5XX Http Statusがない
    - ありえない
        - 少なくとも予期せぬエラーで500は絶対に返すことある
---
#### Step 2
- Declare input variable as to be independen from form data
- 独立した項目として入力値を定義する

---
#### Step 3
- Disable default response definition
- デフォルトレスポンスの定義を消す

---
#### Step 4
- Declare proper response http status
- 返す可能性のあるHttp Statusを正しく定義する

---
#### Step 5
- Add descriptive annotation of Swagger into DTO classes.
- Swaggerの記述用アノテーションをDTOクラスに付与する

---
#### Step 6
- Add descriptive annotation of Swagger into Error response DTO classes.
- Swaggerの記述用アノテーションをエラーレスポンスのDTOクラスに付与する

---
## Any Questions or Comments ?
---
## Fin
