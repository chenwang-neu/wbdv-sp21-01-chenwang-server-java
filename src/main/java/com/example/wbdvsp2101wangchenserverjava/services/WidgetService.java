package com.example.wbdvsp2101wangchenserverjava.services;

import com.example.wbdvsp2101wangchenserverjava.models.Widget;
import com.example.wbdvsp2101wangchenserverjava.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository repository;

    private List<Widget> widgets = new ArrayList<Widget>();
    {
        Widget w1 = new Widget(123l, "ABC123", "HEADING", 1, "Widgets for topic ABC123");
        Widget w2 = new Widget(234l, "ABC123", "PARAGRAPH", 1, "Lorem Ipsum");
        Widget w3 = new Widget(345l, "ABC234", "HEADING", 2, "Widgets for topic ABC234");
        Widget w4 = new Widget(456l, "ABC234", "PARAGRAPH", 1, "Welcome to Widget List");
        Widget w5 = new Widget(567l, "ABC234", "PARAGRAPH", 1, "Welcome to Widget List");

        widgets.add(w1);
        widgets.add(w2);
        widgets.add(w3);
        widgets.add(w4);
        widgets.add(w5);
    }

    // implement crud operations
    public Widget createWidgetForTopic(String topicId, Widget widget) {
        widget.setTopicId(topicId);
        return repository.save(widget);
    }
    public List<Widget> findAllWidgets() {
        return (List<Widget>) repository.findAll();
        //return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        return repository.findWidgetsForTopic(topicId);
    }
    public Widget findWidgetById(Long id) {
        return repository.findById(id).get();
    }

    public Integer updateWidget(Long id, Widget newWidget) {
        Widget originalWidget = findWidgetById(id);

        originalWidget.setText(newWidget.getText());
        originalWidget.setText(newWidget.getText());
        originalWidget.setTopicId(newWidget.getTopicId());
        originalWidget.setType(newWidget.getType());
        originalWidget.setText(newWidget.getText());
        originalWidget.setSize(newWidget.getSize());
        originalWidget.setWidgetOrder(newWidget.getWidgetOrder());
        originalWidget.setWidth(newWidget.getWidth());
        originalWidget.setHeight(newWidget.getHeight());
        originalWidget.setSrc(newWidget.getSrc());
        originalWidget.setCssClass(newWidget.getCssClass());
        originalWidget.setStyle(newWidget.getStyle());
        originalWidget.setName(newWidget.getName());
        originalWidget.setValue(newWidget.getValue());

        repository.save(originalWidget);
        return 1;
    }
    public Integer deleteWidget(Long id) {
        repository.deleteById(id);
        return 1;
    }
}
