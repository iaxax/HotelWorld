package service.impl;

import dao.intf.MemberDAO;
import service.intf.Member;
import vo.member.RegisterVO;
import vo.result.ResultVO;

public class MemberBean implements Member {
        
        private MemberDAO member;

        @Override
        public ResultVO register(RegisterVO vo) {
                boolean isExist = member.isMemberExist(vo.getIdCard());
                if (isExist) {
                        return new ResultVO(false, "您已经申请过会员卡");
                }
                return member.createMember(vo.toPO());
        }

        public void setMember(MemberDAO member) {
                this.member = member;
        }

}
