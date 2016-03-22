package process.model.filter;


public interface IFilterFactory {
    public IImageFilter createFilter(String filePath);
}

