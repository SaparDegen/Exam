package peaksoft.service.serviceImpl;

import peaksoft.dao.dao.MediaDao;
import peaksoft.dao.daoImpl.MediaDaoHibernateImpl;
import peaksoft.model.Socialmedia;
import peaksoft.service.service.SocialMediaService;
import java.util.List;

public class SocialMediaServiceImpl implements SocialMediaService {
    MediaDao mediaDao = new MediaDaoHibernateImpl();

    @Override
    public void saveSocialMedia(Socialmedia socialmedia) {
        try {
            mediaDao.saveSocialMedia(socialmedia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateSocialMedia(Socialmedia socialmedia) {
        mediaDao.updateSocialNedia(socialmedia);
    }

    @Override
    public Socialmedia getById(Long socialMediaId) {
        return mediaDao.getById(socialMediaId);
    }

    @Override
    public List<Socialmedia> getAll() {
        return mediaDao.getAll();
    }

    @Override
    public void deleteById(Long socialMediaId) {
        mediaDao.deleteById(socialMediaId);
    }
}
