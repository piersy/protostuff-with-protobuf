require './generated/IdMessage.pb'
require './generated/UserAccountsMessage.pb'

require 'open-uri'
require "test/unit"

class TestNAME < Test::Unit::TestCase

  def test_sample
    assert_equal(4, 2+2)
  end

  def test_user_account_protostuff_deserialisation
    ua =  UserAccounts::UserAccount.new
    messageFile = File.new("../proto-messages/UserAccountMessage.protostuff", "r")
    ua.parse_from messageFile
    assert_equal(ua.userName, 'userName')
    assert_equal(ua.accountType, 'accountType')
    assert_equal(ua.password, 'password')
  end

  def test_user_account_protostuff_deserialisation
      ua =  UserAccounts::UserAccount.new
      messageFile = File.new("../proto-messages/UserAccountMessage.protobuf", "r")
      ua.parse_from messageFile
      assert_equal(ua.userName, 'userName')
      assert_equal(ua.accountType, 'accountType')
      assert_equal(ua.password, 'password')
    end

    def test_user_accounts_protostuff_deserialisation
        uas =  UserAccounts.new
        messageFile = File.new("../proto-messages/UserAccountsMessage.protostuff", "r")
        uas.parse_from messageFile
        ua = uas.userAccounts[0]
        assert_equal(ua.userName, 'userName')
        assert_equal(ua.accountType, 'accountType')
        assert_equal(ua.password, 'password')
      end

      def test_user_accounts_protostuff_deserialisation
          uas =  UserAccounts.new
          messageFile = File.new("../proto-messages/UserAccountsMessage.protobuf", "r")
          uas.parse_from messageFile
          ua = uas.userAccounts[0]
          assert_equal(ua.userName, 'userName')
          assert_equal(ua.accountType, 'accountType')
          assert_equal(ua.password, 'password')
        end



end