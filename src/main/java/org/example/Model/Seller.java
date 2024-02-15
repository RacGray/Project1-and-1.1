package org.example.Model;

import java.util.Objects;

public class Seller
{
    public String validName;
    public Seller(){
    }

    public Seller(String validName)
    {
        this.validName = validName;
    }

    public String getValidName()
    {
        return validName;
    }

    public void setValidName(String validName)
    {
        this.validName = validName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Seller seller = (Seller) o;
        return Objects.equals(validName, seller.validName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(validName);
    }

    @Override
    public String toString()
    {
        return "Seller{" +
                "ValidName='" + validName + '\'' +
                '}';
    }

}
