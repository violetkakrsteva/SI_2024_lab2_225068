package org.example;

import java.util.List;




public class SILab2 {
    public static boolean checkCart(List<Item> allItems, int payment){  //1
        if (allItems == null){ //1
            throw new RuntimeException("allItems list can't be null!"); //2
        }

        float sum = 0; //3

        for (int i = 0; i < allItems.size(); i++){ //4.1 4.2 4.3
            Item item = allItems.get(i);  //5
            if (item.getName() == null || item.getName().length() == 0){  //6
                item.setName("unknown");  //7
            }
            if (item.getBarcode() != null){  //8
                String allowed = "0123456789";   //9
                char chars[] = item.getBarcode().toCharArray();
                for (int j = 0; j < item.getBarcode().length(); j++){  //10.1 10.2 10.3
                    char c = item.getBarcode().charAt(j);  //11
                    if (allowed.indexOf(c) == -1){  //12
                        throw new RuntimeException("Invalid character in item barcode!");  //13
                    }
                }
                if (item.getDiscount() > 0){ //14
                    sum += item.getPrice()*item.getDiscount();  //15
                }
                else {
                    sum += item.getPrice();  //16
                }
            }   // 17
            else {
                throw new RuntimeException("No barcode!");  //18
            }
            if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0'){  //19
                sum -= 30;  //20
            }
        }
        if (sum <= payment){  //21
            return true;  //22
        }
        else {
            return false;  //23
        }
    }  //24

    public static class Item {
        String name;
        String barcode; //numerical
        int price;
        float discount;

        public Item(String name, String code, int price, float discount) {
            this.name = name;
            this.barcode = code;
            this.price = price;
            this.discount = discount;
        }

        public String getName() {
            return name;
        }

        public String getBarcode() {
            return barcode;
        }

        public int getPrice() {
            return price;
        }

        public float getDiscount() {
            return discount;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBarcode(String code) {
            this.barcode = code;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setDiscount(float discount) {
            this.discount = discount;
        }
    }
}