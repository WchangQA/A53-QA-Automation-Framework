package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
public class AlbumPage extends BasePage{

    @FindBy (xpath="//a[@href='#!/albums']")
    protected WebElement albumLink;
    @FindBy (xpath= "//*[@id='albumsWrapper']//article[@title='Airbit by Makaih Beats']")
    protected WebElement songElement;

    @FindBy (xpath= "//*[@id='albumsWrapper']//span[contains(@style,'https://qa.koel.app/img/covers/unknown-album.png')]")
    protected WebElement albumOneImage;
    @FindBy (xpath= "//*[@id='albumsWrapper']//span[contains(@style,'.jpeg')]")
    protected WebElement albumTwoImage;

    @FindBy (xpath = "//*[@id='albumsWrapper']//div[@class='info']//a[contains(text(),'Airbit')]")
    protected WebElement albumOneTitle;

    @FindBy(xpath= "//*[@id='albumsWrapper']//div[@class='info']//a[contains(text(),'Makaih Beats')]")
    protected WebElement albumOneArtist;

    @FindBy(xpath="//*[@id='albumsWrapper']//p[@class='meta']//span[@class='left']")
    protected WebElement songCount; //Text can be parsed

    @FindBy(xpath="//*[@id='albumsWrapper']//p[@class='meta']//i[@class='fa fa-random']")
    protected WebElement shuffleBtn;

    @FindBy(xpath="//*[@id='albumsWrapper']//p[@class='meta']//i[@class='fa fa-download']")
    protected WebElement downloadBtn;

    //*[@id='albumsWrapper']//div[@class='info']//a[@class='name']
    //*[@id='albumsWrapper']//div[@class='info']//a[@class='artist']
    //Song count
    //*[@id='albumsWrapper']//p[@class='meta']//span[@class='left']
    //shuffle
    //*[@id='albumsWrapper']//p[@class='meta']//i[@class='fa fa-random']
    public AlbumPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    public AlbumPage loadAlbumPage(){
        albumLink.click();
        return this;
    }

    public AlbumPage selectSongElement(){
        doubleClick(songElement);
        return this;
    }

    public boolean albumDefaultImageExists(){
        WebElement albumOneImageWait = findElement(albumOneImage);
        return albumOneImageWait.isDisplayed();
    }
    public boolean albumCoverImageExists(){
        WebElement albumTwoImageWait = findElement(albumTwoImage);
        return albumTwoImageWait.isDisplayed();
    }

    public boolean albumTitleExists(){
        WebElement albumOneTitleWait = findElement(albumOneTitle);
        return albumOneTitleWait.isDisplayed();
    }
    public boolean albumArtistExists(){
        WebElement albumOneArtistWait = findElement(albumOneArtist);
        return albumOneArtistWait.isDisplayed();
    }

    public boolean shuffleBtnExists(){
        moveToAlbumControlsLocation(shuffleBtn);
        return shuffleBtn.isDisplayed();
    }

    public boolean downloadBtnExists(){
        moveToAlbumControlsLocation(downloadBtn);
        return shuffleBtn.isDisplayed();
    }


    public String getAlbumSongCount(){
        WebElement songCountWait = findElement(songCount);
        return songCountWait.getText().substring(0,1);
    }
}
