<script lang="ts" setup>
  import { BasicTable, TableAction, useTable } from '/@/components/Table';
  import { useModal } from '/@/components/Modal';
  import { list#(name.pascal.singular), delete#(name.pascal.singular) } from '/@/apis/#(name.kebab.plural)';
  import { YesNo } from '/@/enums/YesNo';
  import #(name.pascal.singular)Modal from './#(name.pascal.singular)Modal.vue';
  import { columns, searchFormSchema } from './#(name.pascal.singular).data';

  const [register, { reload }] = useTable({
    title: '#(description)列表',
    columns: columns,
    actionColumn: {
      width: 120,
      title: '操作',
      dataIndex: 'action',
      fixed: undefined,
    },
    api: list#(name.pascal.singular),
    showIndexColumn: false,
    bordered: true,
    showTableSetting: true,
    canResize: false,
    useSearchForm: true,
    formConfig: {
      labelWidth: 100,
      schemas: searchFormSchema,
    },
  });
  const [registerModal, { openModal }] = useModal();
  function handleCreate() {
    openModal(true, {
      isUpdate: false,
    });
  }
  function handleEdit(record: Recordable) {
    openModal(true, {
      record,
      isUpdate: true,
    });
  }
  async function handleDelete(record: Recordable) {
    await delete#(name.pascal.singular)(record.id);
    reload();
  }
  function handleSuccess() {
    reload();
  }
</script>
<template>
  <div>
    <basic-table @register="register">
      <template #toolbar>
        <a-button v-auth="'#(name.kebab.singular).create'" type="primary" @click="handleCreate"> 新增#(description) </a-button>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <table-action
            :actions="[
              {
                icon: 'clarity:note-edit-line',
                tooltip: '编辑',
                auth: '#(name.kebab.singular).update',
                onClick: handleEdit.bind(null, record),
              },
              {
                icon: 'ant-design:delete-outlined',
                tooltip: '删除',
                color: 'error',
                auth: '#(name.kebab.singular).delete',
                popConfirm: {
                  title: '是否确认删除',
                  placement: 'left',
                  confirm: handleDelete.bind(null, record),
                },
              },
            ]"
          />
        </template>
      </template>
    </basic-table>
    <#(name.kebab.singular)-modal @register="registerModal" @success="handleSuccess" />
  </div>
</template>
