/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomarbeit_projekt.data;

/**
 *
 * @author Florian
 */
public class User
{
    private final String user_name, user_password;

    public User(String user_name, String user_password)
    {
        this.user_name = user_name;
        this.user_password = user_password;
    }

    public String getUser_name()
    {
        return user_name;
    }

    public String getUser_password()
    {
        return user_password;
    }
    
}
