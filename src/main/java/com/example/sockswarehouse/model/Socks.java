package com.example.sockswarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socks {

    private Color color;
    private Size size;
    @Positive
    @Max(100)
    private int cottonPart;
    @Positive(message = "количество пар носков, целое число больше 0")
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && color == socks.color && size == socks.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPart);
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }
}
