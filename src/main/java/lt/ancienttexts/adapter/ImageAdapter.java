package lt.ancienttexts.adapter;

public interface ImageAdapter {
    byte[] fetchTabletImage(Long id);
    long createTabletImage(byte[] blob);
    void updateTabletImage(long id, byte[] newBlob);
    void deleteTabletImage(long id);
}
