package lt.ancienttexts.adapter;

import java.util.NoSuchElementException;

public interface ImageAdapter {
    byte[] fetchTabletImage(Long id) throws NoSuchElementException;
}
