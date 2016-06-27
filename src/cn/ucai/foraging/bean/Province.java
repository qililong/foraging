package cn.ucai.foraging.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/6/3.
 */
public class Province implements Serializable {
    String name;
    City[] citys;

    public Province(String name, City[] citys) {
        this.name = name;
        this.citys = citys;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                ", citys=" + Arrays.toString(citys) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Province)) return false;

        Province province = (Province) o;

        if (!getName().equals(province.getName())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(getCitys(), province.getCitys());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + Arrays.hashCode(getCitys());
        return result;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City[] getCitys() {
        return citys;
    }

    public void setCitys(City[] citys) {
        this.citys = citys;
    }
}
