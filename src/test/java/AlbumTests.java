import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlbumPage;
import pages.HomePage;
import pages.LoginPage;

public class AlbumTests extends BaseTest{
    @BeforeMethod
    public void loginToKoel(){
        LoginPage login = new LoginPage(getThreadLocal());
        login.loginUsertoKoel();
    }
    @Test
    public void albumCoverCheck() {
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertTrue(homePage.avatarIsDisplayed());
        AlbumPage albumPage = new AlbumPage(getThreadLocal());
        albumPage.loadAlbumPage();
        Assert.assertTrue(albumPage.albumCoverImageExists());
    }

    @Test
    public void albumDefaultCoverCheck() {
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertTrue(homePage.avatarIsDisplayed());
        AlbumPage albumPage = new AlbumPage(getThreadLocal());
        albumPage.loadAlbumPage();
        Assert.assertTrue(albumPage.albumDefaultImageExists());
    }

    @Test
    public void albumNameCheck() {
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertTrue(homePage.avatarIsDisplayed());
        AlbumPage albumPage = new AlbumPage(getThreadLocal());
        albumPage.loadAlbumPage();
        Assert.assertTrue(albumPage.albumTitleExists());
    }

    @Test
    public void albumArtistCheck() {
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertTrue(homePage.avatarIsDisplayed());
        AlbumPage albumPage = new AlbumPage(getThreadLocal());
        albumPage.loadAlbumPage();
        Assert.assertTrue(albumPage.albumArtistExists());
    }
    @Test
    public void albumSongCountCheck() {
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertTrue(homePage.avatarIsDisplayed());
        AlbumPage albumPage = new AlbumPage(getThreadLocal());
        albumPage.loadAlbumPage();
        String albumSongCount = albumPage.getAlbumSongCount();
        int songCount = Integer.parseInt(albumSongCount);
        Assert.assertTrue(1 <=songCount);
    }

    @Test
    public void albumShuffleBtnCheck() {
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertTrue(homePage.avatarIsDisplayed());
        AlbumPage albumPage = new AlbumPage(getThreadLocal());
        albumPage.loadAlbumPage();
        Assert.assertTrue(albumPage.shuffleBtnExists());
    }
    @Test
    public void albumDownloadBtnCheck() {
        HomePage homePage = new HomePage(getThreadLocal());
        Assert.assertTrue(homePage.avatarIsDisplayed());
        AlbumPage albumPage = new AlbumPage(getThreadLocal());
        albumPage.loadAlbumPage();
        Assert.assertTrue(albumPage.downloadBtnExists());
    }
}
