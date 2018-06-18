
package com.example.ssaloni.navigationmvvm.Util;

public class Contact
{

    // private variables
    int id;
    // String name;
    String image_url;

    // Empty constructor
    public Contact()
    {

    }

    // constructor
    public Contact(int keyId, String image_url)
    {
        this.id = keyId;
        this.image_url = image_url;

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getImage_url()
    {
        return image_url;
    }

    public void setImage_url(String image_url)
    {
        this.image_url = image_url;
    }
}