package com.elcentr.service.impl;

import com.elcentr.dao.ProductDAO;
import com.elcentr.model.Product;
import com.elcentr.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public Product create(Product product) {
        if (isNull(product.getId()) &&
                nonNull(product.getAmount()) &&
                StringUtils.isNotBlank(product.getName())
        ) {
            product.setCode(createCodeProduct());
            product.setTimeRegistration(new Date().getTime());
            return productDAO.save(product);
        }
        throw new RuntimeException("Product can not be created.");
    }

    @Override
    public Product update(Product product) {
        Product savedProduct = findOneById(product.getId());
        if (!product.getCode().equals(savedProduct.getCode()) &&
                productDAO.findByCode(product.getCode()) != null) {
            throw new RuntimeException("Product code can not be updated. Code product in database is present");
        }
        if (nonNull(savedProduct.getId()) &&
                nonNull(product.getTimeRegistration()) &&
                nonNull(product.getAmount()) &&
                StringUtils.isNotBlank(product.getCode()) &&
                StringUtils.isNotBlank(product.getName())) {
            return productDAO.save(product);
        }
        throw new RuntimeException("Product can not be updated.");

    }

    @Override
    public Product findOneById(Integer id) {
        return productDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Product was not found"));
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        productDAO.deleteById(id);
    }

    @Override
    public List<Product> findAllByFilter(String name, String code, Integer nominalCurrent, Integer indexProtectionProduct) {
        List<Product> listByFilter = findAll();

        if (name != null) {
            listByFilter = listByFilter.stream()
                    .filter(l -> l.getName().contains(name))
                    .collect(Collectors.toList());
        }

        if (code != null) {
            listByFilter = listByFilter.stream()
                    .filter(l -> l.getCode().contains(code))
                    .collect(Collectors.toList());
        }

        if (nominalCurrent != null) {
            listByFilter = listByFilter.stream()
                    .filter(l -> l.getNominalCurrent().equals(nominalCurrent))
                    .collect(Collectors.toList());
        }

        if (indexProtectionProduct != null) {
            listByFilter = listByFilter.stream()
                    .filter(l -> l.getIndexProtectionProduct().equals(indexProtectionProduct))
                    .collect(Collectors.toList());
        }

        return listByFilter;
    }

    public String createCodeProduct() {
        List<String> codesProduct = findAll().stream()
                .map(Product::getCode)
                .collect(Collectors.toList());

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int maxNumberByDate = 0;

        for (String codeProduct : codesProduct) {
            String[] codeStr = codeProduct.split(" ");
            if (codeStr.length == 4) {
                if (Integer.parseInt(codeStr[0]) == year && Integer.parseInt(codeStr[1]) == month && Integer.parseInt(codeStr[2]) == day) {
                    int num = Integer.parseInt(codeStr[3]);
                    if (num >= maxNumberByDate) {
                        maxNumberByDate = num;
                    }
                }
            }
        }

        return year + " " + checkFormat(month) + " " + checkFormat(day) + " " + checkFormat(maxNumberByDate + 1);
    }

    public static String checkFormat(Integer num) {
        String checkNum;
        if (Integer.toString(num).length() == 1) {
            checkNum = "0" + num;
        } else {
            checkNum = String.valueOf(num);
        }
        return checkNum;
    }

}
