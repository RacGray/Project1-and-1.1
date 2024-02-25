package org.example.Model;

import java.util.Objects;

public class Seller
{
    public int id;
    public String validName;


    public Seller()
    {

    }
    public Seller(int id, String validName){
        this.id = id;
        this.validName = validName;
    }

    public Seller(String validName)
    {
        this.validName = validName;
    }

    public String getValidName()
    {
        return validName;
    }

    public void setValidName()
    {
        this.validName = validName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Seller))
        {
            return false;
        }
        Seller seller = (Seller) o;
        return id == seller.id && Objects.equals(validName, seller.validName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, validName);
    }

    @Override
    public String toString()
    {
        return "Seller{" +
                "id=" + id +
                ", validName='" + validName + '\'' +
                '}';
    }
}
