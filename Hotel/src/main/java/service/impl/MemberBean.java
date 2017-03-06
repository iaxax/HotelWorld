package service.impl;

import dao.intf.MemberDAO;
import service.intf.Member;
import vo.member.ActivateVO;
import vo.member.CancelVO;
import vo.member.RechargeVO;
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
                return member.registerMember(vo.toPO());
        }

        public void setMember(MemberDAO member) {
                this.member = member;
        }

        @Override
        public ResultVO activate(ActivateVO vo) {
                boolean isBankValid = this.member.isBankAccountValid(
                                vo.getAccountId(), vo.getAccountPw()
                );
                if (!isBankValid) {
                        return new ResultVO(false, "银行账号与密码不匹配");
                }
                
                int balance = this.member.getAccountBalance(vo.getAccountId());
                if (balance < vo.getMoney()) {
                        return new ResultVO(false, "银行账户余额不足");
                }
                
                return this.member.activateMember(vo);
        }

        @Override
        public ResultVO cancel(CancelVO vo) {
                boolean isExist = this.member.isAccountExist(vo.getAccountId());
                if (!isExist) {
                        return new ResultVO(false, "该银行账户不存在");
                }
                return this.member.cancelMember(vo);
        }

        @Override
        public ResultVO recharge(RechargeVO vo) {
                boolean isValid = this.member.isBankAccountValid(
                                vo.getAccountId(), vo.getPassword()
                );
                if (!isValid) {
                        return new ResultVO(false, "银行账号与密码不匹配");
                }
                
                int balance = this.member.getAccountBalance(vo.getAccountId());
                if (balance < vo.getMoney()) {
                        return new ResultVO(false, "银行卡余额不足");
                }
                
                return this.member.rechargeMember(vo);
        }

}
