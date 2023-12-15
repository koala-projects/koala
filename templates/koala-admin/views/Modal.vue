<script lang="ts" setup>
  import { ref, unref, computed } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, useForm } from '/@/components/Form/index';
  import { formSchema } from './#(name.kebab.singular).data';
  import { #(name.pascal.singular)Entity, create#(name.pascal.singular), update#(name.pascal.singular) } from '/@/apis/#(name.kebab.plural)';
  
  const isUpdate = ref(false);
  const id = ref<#(id.type.ts) | null>(null);
  const getTitle = computed(() => (!unref(isUpdate) ? '新增#(description)' : '编辑#(description)'));
  const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
    labelWidth: 100,
    baseColProps: { span: 24 },
    schemas: formSchema,
    showActionButtonGroup: false,
  });
  const [registerModal, { setModalProps, closeModal }] = useModalInner(async (data) => {
    resetFields();
    setModalProps({ confirmLoading: false });
    isUpdate.value = !!data?.isUpdate;
    if (unref(isUpdate)) {
      id.value = data.record.id;
      setFieldsValue({
        ...data.record,
      });
    }
  });
  const emit = defineEmits(['success', 'register']);
  async function handleSubmit() {
    try {
      const values: #(name.pascal.singular)Entity = await validate();
      setModalProps({ confirmLoading: true });
      if (unref(isUpdate)) {
        await update#(name.pascal.singular)(unref(id)!, values);
      } else {
        await create#(name.pascal.singular)(values);
      }
      closeModal();
      emit('success');
    } finally {
      setModalProps({ confirmLoading: false });
    }
  }
</script>
<template>
  <basic-modal v-bind="$attrs" @register="registerModal" :title="getTitle" @ok="handleSubmit">
    <basic-form @register="registerForm" />
  </basic-modal>
</template>
