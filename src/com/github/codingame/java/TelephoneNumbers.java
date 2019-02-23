package com.github.codingame.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class TelephoneNumbers {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        int counts = 0; // number of telephone ciphers stored
        List<Cipher> roots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String telephone = in.next();
            if(!telephone.isEmpty())
                counts += insertNumber(roots, telephone, 0);
        }

        System.out.println(counts);

    }

    private static int insertNumber(List<Cipher> ciphers, String telephone, int currentIndex) {

        if(currentIndex == telephone.length())
            return 0;

        Cipher current = new Cipher(Integer.parseInt(telephone.charAt(currentIndex)+""));
        if(ciphers.contains(current)) {
            Cipher inList = ciphers.get(ciphers.indexOf(current));
            return insertNumber(inList.getChildren(),telephone,currentIndex + 1);
        }
        else {
            ciphers.add(current);
            return 1 + insertNumber(current.getChildren(), telephone, currentIndex + 1);
        }
    }
}

class Cipher {
    private Integer value;
    private List<Cipher> children = new ArrayList<>();

    Cipher(int v) {
        value = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cipher cipher = (Cipher) o;
        return Objects.equals(value, cipher.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public List<Cipher> getChildren() {
        return children;
    }

}
