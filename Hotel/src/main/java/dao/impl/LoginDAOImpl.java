package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import dao.intf.LoginDAO;
import po.LoginPO;
import po.hotel.EmployeePO;
import po.member.MemberPO;
import util.DBUtil;
import vo.result.ResultVO;

public class LoginDAOImpl implements LoginDAO {

        @Override
        public ResultVO isValidMember(LoginPO po) {
                return isValidAccount(MemberPO.class,
                                "from po.member.MemberPO "
                                + "where (id='" + po.getId() + "' and password='" + po.getPw() + "') "
                                + "or (id_card='" + po.getId() + "' and password='" + po.getPw() + "')"
                );
        }

        @Override
        public ResultVO isValidEmployee(LoginPO po) {
                return isValidAccount(EmployeePO.class,
                                "from po.hotel.EmployeePO "
                                + "where id='" + po.getId() + "' and password='" + po.getPw() + "'"
                );
        }
        
        private ResultVO isValidAccount(Class<?> cls, String hql) {
                Session session = DBUtil.getSession(cls);
                Query<?> query = session.createQuery(hql);
                List<?> list = query.list();
                session.close();
                if (list.isEmpty()) {
                        return new ResultVO(false, "账号与密码不匹配");
                }
                return new ResultVO(true, "登录成功");
        }

}
