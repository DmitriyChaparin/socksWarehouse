package com.example.sockswarehouse.model;

public enum Size {
XS(29,31),S(32,37),M(36,38),L(39,41),XL(42,44),XXL(45,50);
int from;
int to;

    Size(int from, int to) {
        this.from = from;
        this.to = to;
    }
}
