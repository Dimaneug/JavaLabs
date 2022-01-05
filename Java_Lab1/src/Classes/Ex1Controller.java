package Classes;

public class Ex1Controller {
    Ex1 model;
    Ex1View view;

    public Ex1Controller(Ex1 model, Ex1View view) {
        this.model = model;
        this.view = view;
    }

    public void setEx1Strs(String[] strs) {
        model.setStrs(strs);
    }

    public String[] getEx1Strs() {
        return model.getStrs();
    }

    public void updateView() {
        view.printStrs(model.getStrs());
    }
}
