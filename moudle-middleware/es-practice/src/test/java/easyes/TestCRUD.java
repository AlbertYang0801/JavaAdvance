package easyes;

import cn.hutool.json.JSONUtil;
import com.albert.es.EsApplication;
import com.albert.es.easyes.entity.Document;
import com.albert.es.easyes.mapper.DocumentMapper;
import org.dromara.easyes.core.conditions.index.LambdaEsIndexWrapper;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangjunwei
 * @date 2024/8/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class TestCRUD {

    @Autowired
    DocumentMapper documentMapper;

    @Test
    public void testInsert() {
        Document document = new Document();
        document.setId("100");
        document.setContent("测试");
        document.setTitle("标题");
        documentMapper.insert(document);
    }

    @Test
    public void testQuery() {
        LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<Document>().eq(Document::getTitle, "标题");
        Document document = documentMapper.selectOne(wrapper);
        System.out.println(JSONUtil.toJsonStr(document));
    }


}
