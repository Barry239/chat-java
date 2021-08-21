package chat;

import classes.Message;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

public class SelectEmoji {
    // Emoji codes
    private String[] emojisList = {
            "{Slightly_Smiling_Face}",
            "{Upside_Down_Face}",
            "{Smiling_Face}",
            "{Smiling_Emoji_with_Eyes_Opened}",
            "{Smiling_Emoji_with_Smiling_Eyes}",
            "{Smiling_Face_with_Tightly_Closed_Eyes}",
            "{Smiling_with_Sweat}",
            "{Tears_of_Joy}",
            "{Smirk_Face}",
            "{Unamused_Face}",
            "{Sad_Face}",
            "{Relieved}",
            "{Money_Face}",
            "{Tongue_Out}",
            "{Tongue_Out_Emoji_with_Tightly_Closed_Eyes}",
            "{Tongue_Out_Emoji_with_Winking}",
            "{Hugging_Face}",
            "{Smiling_Face_Emoji_with_Blushed_Cheeks}",
            "{Shyly_Smiling_Face}",
            "{Kiss_Emoji_with_Closed_Eyes}",
            "{Kissing_Face_with_Smiling_Eyes}",
            "{Kissing_Face}",
            "{Nerd_with_Glasses}",
            "{Sunglasses}",
            "{Grinning_Emoji_with_Smiling_Eyes}",
            "{Grinmacing_Face}",
            "{Hungry_Face}",
            "{Wink}",
            "{Emoji_Face_without_Mouth}",
            "{Hushed_Face}",
            "{Frowning_Face_with_Open_Mouth}",
            "{Surprised_Face}",
        };
    
    public void select(Message msg, DefaultListModel model) {
        String message = msg.getMessage();
        
        int index = Arrays.asList(emojisList).indexOf(message);
        
        if (index != -1)
            model.addElement(new ImageIcon(getClass().getResource("/images/" + message.substring(1, message.length() - 1) + ".png")));
        else
            model.addElement(msg.getFrom() + ": " + message);
    }
}
