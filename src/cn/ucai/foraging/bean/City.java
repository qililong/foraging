package cn.ucai.foraging.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/3.
 */
public class City implements Serializable {
    String id;
    String city;

    public City(String id, String city) {
        this.id = id;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (!getId().equals(city.getId())) return false;
        return getCity().equals(city.getCity());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCity().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", City='" + city + '\'' +
                '}';
    }
}





