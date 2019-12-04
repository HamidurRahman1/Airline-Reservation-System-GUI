package com.hrs.view.models;

/**
 *  A login class for all, Customer and Admin
 */
public class Login
{
    private Integer loginId;
    private String username;
    private String password;
    
    public Login() {}
    
    public Login(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    public Login(Integer loginId, String username, String password)
    {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public Integer getLoginId()
    {
        return loginId;
    }
    
    public void setLoginId(Integer loginId)
    {
        this.loginId = loginId;
    }
    
    @Override
    public String toString()
    {
        return "Login{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
