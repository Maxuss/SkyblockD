package space.maxus.skyblockd.nms;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BaseByteUtils {

    public static String playerInventoryToBase64(PlayerInventory playerInventory) throws IllegalStateException {
        // this contains contents, armor and offhand (contents are indexes 0 - 35, armor 36 - 39, offhand - 40)
        return itemStackArrayToBase64(playerInventory.getContents());
    }

    public static String itemStackToBase64(ItemStack item) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(1);

            if (item != null) {
                dataOutput.writeObject(item.serializeAsBytes());
            } else {
                dataOutput.writeObject(null);
            }

            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    @Nullable
    public static ItemStack itemStackFromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            byte[] stack = (byte[]) dataInput.readObject();

            ItemStack istack;

            if (stack != null) {
                istack = ItemStack.deserializeBytes(stack);
            } else {
                istack = null;
            }

            dataInput.close();
            return istack;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

    public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(items.length);

            for (ItemStack item : items) {
                if (item != null) {
                    dataOutput.writeObject(item.serializeAsBytes());
                } else {
                    dataOutput.writeObject(null);
                }
            }

            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    @UnknownNullability
    public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];

            for (int Index = 0; Index < items.length; Index++) {
                byte[] stack = (byte[]) dataInput.readObject();

                if (stack != null) {
                    items[Index] = ItemStack.deserializeBytes(stack);
                } else {
                    items[Index] = null;
                }
            }

            dataInput.close();
            return items;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

    public static String itemStackListToBase64(List<ItemStack> data) {
        ArrayList<ItemStack> a;
        return itemStackArrayToBase64(data.toArray(new ItemStack[0]));
    }

    public static List<ItemStack> itemStackListFromBase64(String data) throws IOException {
        return Arrays.asList(itemStackArrayFromBase64(data));
    }
}