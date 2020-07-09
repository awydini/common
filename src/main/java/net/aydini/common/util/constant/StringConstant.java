package net.aydini.common.util.constant;

public enum StringConstant
{
    IS("is");
    
    private String value;

    private StringConstant(String value)
    {
        this.value = value;
    } 

   public String getValue()
   {
       return value;
   }
}
