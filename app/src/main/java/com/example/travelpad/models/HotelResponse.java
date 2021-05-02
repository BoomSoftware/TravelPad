package com.example.travelpad.models;

public class HotelResponse {
    private Result result;
    private String placeId;
    private double pricePerDay;

    public String getReservationPath() {
        return reservationPath;
    }

    public void setReservationPath(String reservationPath) {
        this.reservationPath = reservationPath;
    }

    private String reservationPath;
    private int days;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Result getResult() {
        return result;
    }

    public static class Result {
        private String name;
        private double rating;
        private String formatted_address;
        private String international_phone_number;
        private Photo[] photos;
        private Geometry geometry;

        public Geometry getGeometry() {
            return geometry;
        }

        public static class Geometry {
            private Location location;

            public Location getLocation() {
                return location;
            }

            public static class Location {
                private float lat;
                private float lng;

                public float getLat() {
                    return lat;
                }

                public float getLng() {
                    return lng;
                }
            }
        }

        public Photo[] getPhotos() {
            return photos;
        }

        public String getName() {
            return name;
        }

        public double getRating() {
            return rating;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public String getInternational_phone_number() {
            return international_phone_number;
        }

        public class Photo {
           private String photo_reference;

            public String getPhoto_reference() {
                return photo_reference;
            }

        }
    }
}
