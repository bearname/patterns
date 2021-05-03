package ru.mikushov.ood.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageImplTest {

    @Test
    public void invalidWidth() {
        try {
            new ImageImpl(null, 400, 300);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid image file", e.getMessage());
        }
    }

    @Test
    public void invalidExtension() {
        try {
            new ImageImpl("image.pn", 400, 300);
            fail();
        } catch (Exception e) {
            assertEquals("Invalid image file", e.getMessage());
        }
    }

    @Test
    public void widthLessThanMinValue() {
        try {
            new ImageImpl("image.png", -400, 300);
            fail();
        } catch (Exception e) {
            assertEquals("Width must be in range [1, 10000]", e.getMessage());
        }
    }

    @Test
    public void widthLessThanMaxValue() {
        try {
            new ImageImpl("image.jpeg", 10001, 300);
            fail();
        } catch (Exception e) {
            assertEquals("Width must be in range [1, 10000]", e.getMessage());
        }
    }

    @Test
    public void heightLessThanMinValue() {
        try {
            new ImageImpl("image.png", 400, -300);
            fail();
        } catch (Exception e) {
            assertEquals("Height must be in range [1, 10000]", e.getMessage());
        }
    }

    @Test
    public void heightLessThanMaxValue() {
        try {
            new ImageImpl("image.jpg",  300, 10001);
            fail();
        } catch (Exception e) {
            assertEquals("Height must be in range [1, 10000]", e.getMessage());
        }
    }
}