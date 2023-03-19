package com.xi.common;

public enum Constant {
    REDIS_USER_PREFIX("user:"),
    CODEPREFIX("phoneCode:"),
    HTMLPREFIX("<h1>老北博客</h1>"+
            "<p>您的验证码为：</p>"),
    HTMLSUFFIX("<p>验证码在5分钟内有效，请尽快使用。如果您没有请求此代码，请前往 老北博客 并立即更改密码。如需帮助，请联系 lb16673514910@163.com 。</p>" +
            "<p>感谢您帮助我们一同维护您的帐户安全。</p>" +
            "<p>祝您生活愉快</p>" +
            "<p>老北</p>"),
    EMAILPREFIX("email:");
    private String value;

    Constant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
