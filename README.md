## 專案說明 (Project Title)：
<img width="1280" alt="截圖 2023-07-18 16 20 50" src="https://github.com/allen7270/spring-boot/assets/67735987/ddd825a1-077d-4fb6-bdc3-6c401e17b3ea">


* 圖A

**`Java | Spring Boot | JPA | Mybatis | MySQL | MariaDB | Git | HTML | CSS | JS `**

* **使用 Spring Boot 框架構出後台以便管理資料**
## 功能描述 (features)：
### 查看書籍(圖A) / 新增書籍(圖B)
#### `JPA, Mybatis`
* 透過`搜尋欄`依照條件進行 **篩選** 資料 **`(Mybatis)`** `①`
* 依據後端返回資料透過 **`JS`** 進行 **頁面切換** `④`
* `checkBox ⑤` -> 依據 `uuid`
     - **批次註銷資料** `②` **`(JPA)`** 
     - **批次新增購物車** `③` **`(JPA)`** 
* `放大鏡 ⑥` -> `查看`資料明細，透過`uuid`獲取`書籍詳細資料`
* `修改 ⑦` -> 透過`JSON`格式，將資料傳送透端進行修改 **`(JPA)`**
* 加入購物車後透過 **`websocket`** 管理者收到推播通知 `⑧`

### 註冊/登入/登出(圖C)
#### `Spring Security`
* 註冊時，使用者可以創建帳號，並透過 **`Spring Security`** 進行`登入`驗證與`cookie`記住資料
* 透過 **`Spring Security`** 管理授權`api, JS...`

### 附錄[](https://)
<img width="1280" alt="截圖 2023-07-17 16 39 13" src="https://github.com/allen7270/spring-boot/assets/67735987/ecbbf2d6-48c2-4548-b9fd-6f8fff5a76c1">

* 圖B


<img width="1280" alt="截圖 2023-07-16 11 13 42" src="https://github.com/allen7270/spring-boot/assets/67735987/458a425d-d450-4d53-b29a-3fb98f010eeb">

* 圖C
