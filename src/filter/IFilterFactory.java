package filter;


public interface IFilterFactory {
    public IImageFilter createFilter(String filePath);
}

