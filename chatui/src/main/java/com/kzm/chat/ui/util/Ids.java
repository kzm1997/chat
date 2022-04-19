package com.kzm.chat.ui.util;

/**
 * 组件id
 */
public class Ids {

    public static class ELementTalkId{
        public static String createTalkPaneId(String id){
            return "ElementTalkId_createTalkPaneId_" + id;
        }

        public static String analysisTalkPaneId(String id) {
            return id.split("_")[2];
        }

        public static String createInfoBoxListId(String id) {
            return "ElementTalkId_createInfoBoxListId_" + id;
        }

        public static String analysisInfoBoxListId(String id) {
            return id.split("_")[2];
        }

        public static String createMsgDataId(String id) {
            return "ElementTalkId_createMsgDataId_" + id;
        }

        public static String analysisMsgDataId(String id) {
            return id.split("_")[2];
        }

        public static String createMsgSketchId(String id) {
            return "ElementTalkId_createMsgSketchId_" + id;
        }

        public static String analysisMsgSketchId(String id) {
            return id.split("_")[2];
        }

        public static String createFriendGroupId(String id) {
            return "ElementTalkId_createFriendGroupId_" + id;
        }
    }
}
