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
public class Times
{
    private final String time1, time2, time3, time4; 
    private final Boolean time1_active, time2_active, time3_active, time4_active;

    public Times(String time1, String time2, String time3, String time4, Boolean time1_active, Boolean time2_active, Boolean time3_active, Boolean time4_active)
    {
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.time4 = time4;
        this.time1_active = time1_active;
        this.time2_active = time2_active;
        this.time3_active = time3_active;
        this.time4_active = time4_active;
    }

    public String getTime1()
    {
        return time1;
    }

    public String getTime2()
    {
        return time2;
    }

    public String getTime3()
    {
        return time3;
    }

    public String getTime4()
    {
        return time4;
    }

    public Boolean getTime1_active()
    {
        return time1_active;
    }

    public Boolean getTime2_active()
    {
        return time2_active;
    }

    public Boolean getTime3_active()
    {
        return time3_active;
    }

    public Boolean getTime4_active()
    {
        return time4_active;
    }
        
}
