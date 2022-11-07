package peaksoft.service.service;

import peaksoft.model.Garage;
import peaksoft.model.Socialmedia;
import java.util.List;

public interface SocialMediaService {
    void saveSocialMedia(Socialmedia socialmedia);

    void updateSocialMedia(Socialmedia socialmedia);

    Socialmedia getById(Long socialMediaId);

    List<Socialmedia> getAll();

    void deleteById(Long socialMediaId);
}
