package yunifn.bawe.com.yunifang.dbutil;

/**
 * Created by Lenovo on 2017/3/26.
 * author ：李宁
 * 类注释：
 */

public class GoodBeann {

    private  int  id;
    private  String goodname;
    private  String goo_efficacy;
    private  String good_market_price;
    private  double good_shop_price;
    private  int good_imgint;
    private  int price;
    private  int count;
    private  String good_img;
    private  boolean checkboxxx;

    public GoodBeann() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public double getGood_shop_price() {
        return good_shop_price;
    }

    public void setGood_shop_price(double good_shop_price) {
        this.good_shop_price = good_shop_price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCheckboxxx() {
        return checkboxxx;
    }

    public void setCheckboxxx(boolean checkboxxx) {
        this.checkboxxx = checkboxxx;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public String getGoo_efficacy() {
        return goo_efficacy;
    }

    public void setGoo_efficacy(String goo_efficacy) {
        this.goo_efficacy = goo_efficacy;
    }

    public String getGood_market_price() {
        return good_market_price;
    }

    public void setGood_market_price(String good_market_price) {
        this.good_market_price = good_market_price;
    }



    public int getGood_imgint() {
        return good_imgint;
    }

    public void setGood_imgint(int good_imgint) {
        this.good_imgint = good_imgint;
    }

    public String getGood_img() {
        return good_img;
    }

    public void setGood_img(String good_img) {
        this.good_img = good_img;
    }

    @Override
    public String toString() {
        return "GoodBeann{" +
                "id=" + id +
                ", goodname='" + goodname + '\'' +
                ", goo_efficacy='" + goo_efficacy + '\'' +
                ", good_market_price='" + good_market_price + '\'' +
                ", good_shop_price=" + good_shop_price +
                ", good_imgint=" + good_imgint +
                ", price=" + price +
                ", count=" + count +
                ", good_img='" + good_img + '\'' +
                ", checkboxxx=" + checkboxxx +
                '}';
    }
}
