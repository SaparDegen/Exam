package peaksoft.dao.dao;

import peaksoft.model.Garage;
import peaksoft.model.Socialmedia;
import java.util.List;

public interface MediaDao {
    void saveSocialMedia(Socialmedia socialmedia);

    void updateSocialNedia(Socialmedia socialmedia);

    Socialmedia getById(Long socialMediaId);

    List<Socialmedia> getAll();

    void deleteById(Long socialMediaId);
}
