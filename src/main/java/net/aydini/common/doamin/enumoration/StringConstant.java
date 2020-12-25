package net.aydini.common.doamin.enumoration;

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
