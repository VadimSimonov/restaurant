package restaurant.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import restaurant.model.Menu;

import java.time.LocalDate;
import java.util.List;

import static restaurant.MealTestData.*;
import static restaurant.MenuTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuServiceImplTest {
    @Autowired
    private MenuService service;

    @Test
    public void create() throws Exception {
      //  service.create(CREATEMENU,Restaurant_ID2);
        assertMatch(service.getAll(),MENU1,MENU2,CREATEMENU);
    }

    @Test
    public void delete() throws Exception {
        service.delete(Menu_ID2);
        assertMatch(service.getAll(),MENU1);
    }

    @Test
    public void get() throws Exception {
        Menu menu = service.get(Menu_ID2,Restaurant_ID2);
        Assert.assertEquals(1,menu.getRestaurants().size());
        assertMatch(menu,MENU2);
    }

    @Test
    public void update() throws Exception {
        Menu update = MENU1;
        update.setDate(LocalDate.of(2020,01,01));
     //   service.update(update, Restaurant_ID1);
        assertMatch(service.get(Menu_ID1, Restaurant_ID1),update);
    }

    @Test
    public void getAll() throws Exception {
        List<Menu> all = service.getAll();
        Assert.assertEquals(2,all.size());
        Assert.assertEquals(2,all.get(0).getRestaurants().size());
        Assert.assertEquals(1,all.get(1).getRestaurants().size());
        assertMatch(all,MENU1,MENU2);
    }

}