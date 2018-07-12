package whustore.service;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whustore.dao.ProductDao;
import whustore.data.ProductData;
import whustore.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShopService {
    ProductDao productDao;

    public List<Product> getAllProdut() {
        productDao = new ProductDao();
        return productDao.getAllProducts();
    }

    /**
     * 根据输入的搜索信息搜索商品
     *
     * @param info 输入的信息
     * @return 商品list
     */
    public List<Product> getProductsBySearch(String info) {
        List<Product> allList = ProductData.getProductList();
        List<Product> resultList = new ArrayList<>();
        if (info.length() > 3) {
            //搜索信息长度大于三需要切割搜索信息的
            List<String> words = new ArrayList<>();
            words.add(info);
            List<Product> searchResult = new ArrayList<>();
            searchResult = ProductData.getProductContainsKeywords(words);
            //有直接匹配对象直接返回
            if (searchResult.size() > 0)
                return searchResult;
                //没有直接返回对象切割信息
            else {


                for (int subLength = info.length() - 1; subLength >= 3; subLength--) {
                    for (int beginIndex = 0; beginIndex <= info.length() - subLength; beginIndex++) {
                        try {
                            int endIndex = beginIndex+subLength;
                            words.add(info.substring(beginIndex,endIndex));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                List<Term> terms = ToAnalysis.parse(info).getTerms();
                filterAndSort(terms, words);

                return ProductData.getProductContainsKeywords(words);
            }

        } else if (info.length() == 3) {
            //搜索信息长度等于三
            List<Term> terms = ToAnalysis.parse(info).getTerms();
            List<String> words = new ArrayList<>();
            words.add(info);
            filterAndSort(terms, words);
            List<Product> searchResult = ProductData.getProductContainsKeywords(words);
            return searchResult;
        } else {
            for (Product product :
                    allList) {
                if (product.getProductName().contains(info))
                    resultList.add(product);
            }
            return resultList;
        }
    }

    /**
     * 筛选分词结果并根据长度排序
     *
     * @param terms 分词结果
     * @param words 用于返回筛选后的长度降序的list
     */
    private void filterAndSort(List<Term> terms, List<String> words) {
        int maxLength = 0;
        List<String> filteredWords = new ArrayList<>();
        //对分词进行筛选
        for (Term term :
                terms) {
            String word = term.getName();
            String natureStr = term.getNatureStr();
            if (expectedNature.contains(natureStr)) {
                int currentWordLength = word.length();
                filteredWords.add(word);
                maxLength = maxLength > currentWordLength ? maxLength : currentWordLength;
            }
        }
        //对筛选后对分词进行排序
        for (int i = maxLength; i > 0; i--) {
            for (String word :
                    filteredWords) {
                if (word.length() == i)
                    words.add(word);
            }
        }
    }

    private Set<String> expectedNature = new HashSet<String>() {{
        add("n");
        add("v");
        add("vd");
        add("vn");
        add("vf");
        add("vx");
        add("vi");
        add("vl");
        add("vg");
        add("nt");
        add("nz");
        add("nw");
        add("nl");
        add("ng");
        add("userDefine");
        add("wh");
    }};
}
