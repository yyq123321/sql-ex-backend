import Footer from '@/components/Footer';
import {register} from '@/services/ant-design-pro/api';
import {getFakeCaptcha} from '@/services/ant-design-pro/login';
import {LockOutlined, MobileOutlined, UserOutlined,} from '@ant-design/icons';
import {LoginForm, ProFormCaptcha, ProFormText,} from '@ant-design/pro-components';
import { message, Tabs} from 'antd';
import React, {useState} from 'react';
import {history} from 'umi';
import styles from './index.less';
import {SYSTEM_LOGO, YYQ_LINK} from "@/constant";


const Register: React.FC = () => {
  const [type, setType] = useState<string>('account');

  const handleSubmit = async (values: API.RegisterParams) => {
    const { userPassword, checkPassword} = values
    if(userPassword !== checkPassword) {
      message.error('两次输入的密码不一致');
      return;
    }
    try {
      // 注册
      const id = await register(values);
      if (id) {
        const defaultLoginSuccessMessage = '注册成功！';
        message.success(defaultLoginSuccessMessage);
        /** 此方法会跳转到 redirect 参数所在的位置 */
        if (!history) return;
        const { query } = history.location;
        // const { redirect } = query as {
        //   redirect: string;
        // };
        history.push({
          pathname: '/user/login',
          query,
        });
        return;
      }
    } catch (error: any) {
      const defaultLoginFailureMessage = '注册失败，请重试！';
      message.error(error.message ?? defaultLoginFailureMessage);
    }
  };
  return (
    <div className={styles.container}>
      <div className={styles.content}>
        <LoginForm
          submitter={{
            searchConfig: {
              submitText: '注册'
            }
          }}
          logo={<img alt="logo" src={SYSTEM_LOGO} />}
          title="User Center"
          subTitle={<a href={YYQ_LINK} target="_blank"> yyq github </a>}
          initialValues={{
            autoLogin: true,
          }}
          onFinish={async (values) => {
            await handleSubmit(values as API.RegisterParams);
          }}
        >
          <Tabs activeKey={type} onChange={setType}>
            <Tabs.TabPane key="account" tab={'账号密码注册'} />
            <Tabs.TabPane key="mobile" tab={'手机号注册'} />
          </Tabs>

          {type === 'account' && (
            <>
              <ProFormText
                name="userAccount" fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'请输入账号'}
                rules={[
                  {
                    required: true,
                    message: '账号是必填项！',
                  },
                ]}
              />
              <ProFormText.Password
                name="userPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'请输入密码'}
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },
                  {
                    len: 6,
                    type: 'string',
                    message: '密码最小六位',
                  }
                ]}
              />
              <ProFormText.Password
                name="checkPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'请再次输入密码'}
                rules={[
                  {
                    required: true,
                    message: '再次密码是必填项！',
                  },
                  {
                    len: 6,
                    type: 'string',
                    message: '密码最小六位',
                  }
                ]}
              />
              <ProFormText
                name="planetCode" fieldProps={{
                size: 'large',
                prefix: <UserOutlined className={styles.prefixIcon} />,
              }}
                placeholder={'请输入唯一编号'}
                rules={[
                  {
                    required: true,
                    message: '唯一编号是必填项！',
                  },
                ]}
              />
            </>
          )}

          {/*{status === 'error' && loginType === 'mobile' && <LoginMessage content="验证码错误" />}*/}
          {type === 'mobile' && (
            <>
              <ProFormText
                fieldProps={{
                  size: 'large',
                  prefix: <MobileOutlined className={styles.prefixIcon} />,
                }}
                name="mobile"
                placeholder={'请输入手机号！'}
                rules={[
                  {
                    required: true,
                    message: '手机号是必填项！',
                  },
                  {
                    pattern: /^1\d{10}$/,
                    message: '不合法的手机号！',
                  },
                ]}
              />
              <ProFormCaptcha
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                captchaProps={{
                  size: 'large',
                }}
                placeholder={'请输入验证码！'}
                captchaTextRender={(timing, count) => {
                  if (timing) {
                    return `${count} ${'秒后重新获取'}`;
                  }
                  return '获取验证码';
                }}
                name="captcha"
                rules={[
                  {
                    required: true,
                    message: '验证码是必填项！',
                  },
                ]}
                onGetCaptcha={async (phone) => {
                  const result = await getFakeCaptcha({
                    phone,
                  });
                  if (result === false) {
                    return;
                  }
                  message.success('获取验证码成功！验证码为：1234');
                }}
              />
            </>
          )}
          <div
            style={{
              marginBottom: 24,
            }}
          >
          </div>
        </LoginForm>
      </div>
      <Footer />
    </div>
  );
};
export default Register;
