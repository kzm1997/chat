package com.kzm.chat.ui.view.chat;


public class ChatController extends ChatInit implements IchatMethod{
    
    
    private ChatView chatView;
    private ChatEventDefine chatEventDefine;
    
    


    public ChatController(IchatEvent ichatEvent) {
        super(ichatEvent);
    }

    @Override
    public void initView() {
        chatView=new ChatView(this,chatEvent);
    }

    @Override
    public void initEventDefine() {
      chatEventDefine=new ChatEventDefine(this,chatEvent,this);
    }

    @Override
    public void doShow() {
       super.show();
    }

    @Override
    public void setUserInfo(String userId, String userNickName, String userHead) {
        
    }
}
