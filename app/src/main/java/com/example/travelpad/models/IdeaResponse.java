package com.example.travelpad.models;

import java.util.List;

public class IdeaResponse {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public static class Result {
        private String name;
        private double rating;
        private String formatted_address;
        private Photo[] photos;
        private Geometry geometry;

        public Geometry getGeometry() {
            return geometry;
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

        public Photo[] getPhotos() {
            return photos;
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

        public static class Photo {
            public String getPhoto_reference() {
                return photo_reference;
            }
            private String photo_reference;
        }
    }
}
