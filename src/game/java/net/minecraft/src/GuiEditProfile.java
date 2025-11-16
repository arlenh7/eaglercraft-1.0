package net.minecraft.src;

public class GuiEditProfile extends GuiScreen {
    private GuiTextField usernameField;

    @Override
    public void initGui() {
        if (this.controlList != null) {
            this.controlList.clear();
        }

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.usernameField = new GuiTextField(this, this.fontRenderer, centerX - 100, centerY - 20, 200, 20, "");
        this.usernameField.setFocused(true);

        this.controlList.add(new GuiButton(1, centerX - 100, centerY + 10, 200, 20, "Done"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiMainMenu());
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        this.usernameField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void updateScreen() {
        this.usernameField.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        this.drawCenteredString(this.fontRenderer, "Select Username", this.width / 2, this.height / 2 - 50, 0xFFFFFF);

        this.usernameField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}