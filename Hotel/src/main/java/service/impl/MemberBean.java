package service.impl;

import service.intf.Member;
import vo.member.RegisterVO;
import vo.result.ResultVO;

public class MemberBean implements Member {

        @Override
        public ResultVO register(RegisterVO vo) {
                return new ResultVO(true, "success");
        }

}
