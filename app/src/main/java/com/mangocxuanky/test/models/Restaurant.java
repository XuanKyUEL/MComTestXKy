package com.mangocxuanky.test.models;

public class Restaurant {
//    "placeName": "Cơm Tấm Đại Đồng",
//            "dishName": "Cơm Sườn",
//            "photo": "lunch_com_tam_dai_dong",
//            "ratingValue": 4.1,
//            "ratingCount": "100+",
//            "address": "38 Đường Số 17, P. Linh Trung, Q. Thủ Đức, Tp.HCM"

    private String placeName;
    private String dishName;
    private String photo;
    private float ratingValue;
    private String ratingCount;
    private String address;

    public Restaurant(String placeName, String dishName, String photo, float ratingValue, String ratingCount, String address) {
        this.placeName = placeName;
        this.dishName = dishName;
        this.photo = photo;
        this.ratingValue = ratingValue;
        this.ratingCount = ratingCount;
        this.address = address;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public float getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(float ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Restaurant restaurant = (Restaurant) obj;
        return Float.compare(restaurant.ratingValue, ratingValue) == 0 &&
                placeName.equals(restaurant.placeName) &&
                dishName.equals(restaurant.dishName) &&
                photo.equals(restaurant.photo) &&
                ratingCount.equals(restaurant.ratingCount) &&
                address.equals(restaurant.address);
    }
}
