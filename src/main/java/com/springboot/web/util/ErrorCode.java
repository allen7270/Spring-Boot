package com.springboot.web.util;

/**
 * ErrorCode Configuration
 */
public enum ErrorCode {
    SUCCESS(200, "200", ""),
    PARAMETER_OUT_OF_RANGE(400, "40001", "傳入參數不在准許值域內"),
    REQUIRED_NOT_PROVIDED(400, "40002", "未提供必要參數"),
    DATA_ALREADY_EXISTS(400, "40003", "資料重複"),
    DATA_EXCEEDS_LIMIT(400, "40004", "資料數量超過上限，請縮小範圍"),
    CHANGE_DATA_NOT_NEW(400, "40005", "異動前資料非最新資料，請進行帶入最新資料"),
    ASSOCIATED_SERVICE_IS_OUT_OF_ORDER(400, "40006", "相關服務中斷中，請稍後再試"),
    CREATE_DATA_ERROR(400, "40007", "建立資料錯誤"),
    DATA_NOT_EXIST(400, "40021", "資料不存在或已註銷"),
    DATA_HAS_BEEN_CONFIRMED(400, "40031", "資料已經確認過，無法重複確認"),
    ILLEGAL_ENTERACCOUNT_ACTION(400, "40032", "不合法的確認(入帳)動作"),
    ILLEGAL_CANCELACCOUNT_ACTION(400, "40033", "不合法的取消確認(入帳)動作"),
    BATCH_ADD_FAIL(400, "40034", "批次新增失敗"),
    ILLEGAL_MODIFY_ACTION(400, "40061", "不合法的異動動作"),
    CANCEL_DATA_NOT_EXIST(400, "40071", "註銷的資料不存在或已註銷"),
    ILLEGAL_DELETE_ACTION(400, "40072", "不合法的註銷動作"),
    UPLOAD_FILE_ANALYSIS_FAIL(400, "40081", "上傳的檔案解析錯誤"),
    UPLOAD_FILE_DATA_IS_EMPTY(400, "40082", "上傳的檔案內容為空"),
    UPLOAD_FILE_DATA_EXCEEDS_LIMIT(400, "40083", "匯入筆數超過上限"),
    SCHEDULE_TASK_CODE_UNDEFINED(400, "40091", "排程工作程式碼未定義"),
    SCHEDULE_TASK_CODE_ERROR(400, "40092", "排程工作程式碼與設定程式碼不相同"),
    PERMISSIONS_ERROR(403, "403", "沒有存取權限"),

    UNKNOW(500, "500", "未知錯誤，請聯絡系統管理員");

    public String errorCode = "";
    public String errorMessage = "";
    public int errorStatus = 200;

    /**
     * Constructor
     *
     * @param errorStatus
     * @param errorCode
     * @param errorMessage
     */
    ErrorCode(int errorStatus, String errorCode, String errorMessage) {
        this.errorStatus = errorStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
